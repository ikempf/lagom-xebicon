/*
 * Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>
 */
package sample.hellostream.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.helloworld.api.HelloService;
import sample.hellostream.api.HelloStream;

public class HelloStreamModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(HelloStream.class, HelloStreamImpl.class));
        bindClient(HelloService.class);
    }
}
