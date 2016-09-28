/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import akka.Done;
import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;

import java.util.Optional;

import javax.inject.Inject;

import sample.helloworld.api.GreetingMessage;
import sample.helloworld.api.HelloService;
import sample.helloworld.impl.HelloCommand.*;

public class HelloServiceImpl implements HelloService {

    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public HelloServiceImpl(PersistentEntityRegistry persistentEntityRegistry) {
        this.persistentEntityRegistry = persistentEntityRegistry;
        persistentEntityRegistry.register(HelloWorld.class);
    }

    @Override
    public ServiceCall<NotUsed, String> hello(String id) {
        return request -> {
            PersistentEntityRef<HelloCommand> ref =
                    persistentEntityRegistry.refFor(HelloWorld.class, id);

            return ref.ask(new Hello(id));
        };
    }

    @Override
    public ServiceCall<GreetingMessage, Done> useGreeting(String id) {
        return request -> {
            PersistentEntityRef<HelloCommand> ref =
                    persistentEntityRegistry.refFor(HelloWorld.class, id);

            return ref.ask(new UseGreetingMessage(request.message));
        };

    }

}
