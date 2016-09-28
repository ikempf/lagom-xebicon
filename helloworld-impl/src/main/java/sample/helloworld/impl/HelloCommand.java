/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import com.lightbend.lagom.serialization.CompressedJsonable;
import com.lightbend.lagom.serialization.Jsonable;

import akka.Done;

import static com.google.common.base.MoreObjects.toStringHelper;

public interface HelloCommand extends Jsonable {

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    public final class UseGreetingMessage implements HelloCommand, CompressedJsonable, PersistentEntity.ReplyType<Done> {
        public final String message;

        @JsonCreator
        public UseGreetingMessage(String message) {
            this.message = Preconditions.checkNotNull(message, "message");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof UseGreetingMessage && equalTo((UseGreetingMessage) another);
        }

        private boolean equalTo(UseGreetingMessage another) {
            return message.equals(another.message);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + message.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return toStringHelper("UseGreetingMessage")
                    .add("message", message).toString();
        }
    }

    @SuppressWarnings("serial")
    @Immutable
    @JsonDeserialize
    final class Hello implements HelloCommand, PersistentEntity.ReplyType<String> {
        public final String name;

        @JsonCreator
        public Hello(String name) {
            this.name = Preconditions.checkNotNull(name, "name");
        }

        @Override
        public boolean equals(@Nullable Object another) {
            if (this == another)
                return true;
            return another instanceof Hello && equalTo((Hello) another);
        }

        private boolean equalTo(Hello another) {
            return name.equals(another.name);
        }

        @Override
        public int hashCode() {
            int h = 31;
            h = h * 17 + name.hashCode();
            return h;
        }

        @Override
        public String toString() {
            return toStringHelper("Hello")
                    .add("name", name).toString();
        }
    }

}
