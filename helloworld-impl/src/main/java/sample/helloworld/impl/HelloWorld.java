/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import java.util.Optional;

import com.lightbend.lagom.javadsl.persistence.PersistentEntity;

import akka.Done;
import sample.helloworld.impl.HelloCommand.Hello;
import sample.helloworld.impl.HelloCommand.UseGreetingMessage;
import sample.helloworld.impl.HelloEvent.GreetingMessageChanged;

public class HelloWorld extends PersistentEntity<HelloCommand, HelloEvent, WorldState> {

    @Override
    public Behavior initialBehavior(Optional<WorldState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(
                snapshotState.orElse(new WorldState("Hello")));

        b.setReadOnlyCommandHandler(Hello.class,
                (cmd, ctx) -> ctx.reply(state().message + ", " + cmd.name + "!"));

        b.setCommandHandler(UseGreetingMessage.class, (cmd, ctx) ->
                ctx.thenPersist(new GreetingMessageChanged(cmd.message),
                        evt -> ctx.reply(Done.getInstance())));

        b.setEventHandler(GreetingMessageChanged.class,
                evt -> new WorldState(evt.message));

        return b.build();
    }

}
