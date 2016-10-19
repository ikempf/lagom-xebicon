/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.helloworld.impl;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.lightbend.lagom.serialization.CompressedJsonable;

@SuppressWarnings("serial")
@Immutable
@JsonDeserialize
public final class WorldState implements CompressedJsonable {

    public final String message;

    @JsonCreator
    public WorldState(String message) {
        this.message = Preconditions.checkNotNull(message, "message");
    }

    @Override
    public boolean equals(@Nullable Object another) {
        if (this == another)
            return true;
        return another instanceof WorldState && equalTo((WorldState) another);
    }

    private boolean equalTo(WorldState another) {
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
        return MoreObjects.toStringHelper("WorldState")
                .add("message", message).toString();
    }
}
