/*
 *  Copyright 2019 Mohammad Taqi Soleimani
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 */
package io.cassandana.broker;

import io.cassandana.broker.security.IAuthenticator;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.netty.channel.Channel;

class MQTTConnectionFactory {

    private final BrokerConfiguration brokerConfig;
    private final IAuthenticator authenticator;
    private final SessionRegistry sessionRegistry;
    private final PostOffice postOffice;
    private PrometheusMeterRegistry pmr;

    MQTTConnectionFactory(BrokerConfiguration brokerConfig, IAuthenticator authenticator,
                          SessionRegistry sessionRegistry, PostOffice postOffice, PrometheusMeterRegistry pmr) {
        this.brokerConfig = brokerConfig;
        this.authenticator = authenticator;
        this.sessionRegistry = sessionRegistry;
        this.postOffice = postOffice;
        this.pmr = pmr;
    }

    public MQTTConnection create(Channel channel) {
        return new MQTTConnection(channel, brokerConfig, authenticator, sessionRegistry, postOffice, pmr);
    }
}
