/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.hellostream.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.*;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

public interface HelloStream extends Service {

    ServiceCall<Source<String, NotUsed>, Source<String, NotUsed>> stream();

    @Override
    default Descriptor descriptor() {
        return named("hellostream")
                .withCalls(namedCall("hellostream", this::stream));
    }
}
