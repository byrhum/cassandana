package io.cassandana.metrics;

import io.micrometer.prometheus.PrometheusMeterRegistry;

public class prometheusCounterConfig {
    PrometheusMeterRegistry pmr;

    public prometheusCounterConfig(PrometheusMeterRegistry pmr){
        this.pmr = pmr;
    }

    public void init(){
        pmr.counter("mqttServerInitialized");
        pmr.counter("mqttHandleMessageConnect");
        pmr.counter("mqttHandleMessage");
    }
}
