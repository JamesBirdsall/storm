package org.apache.storm.eventhubs.core;

import java.time.Instant;

public class TimestampFilter implements IEventFilter {
    Instant time = null;
	  
    public TimestampFilter(Instant time) {
        this.time = time;
    }
	  
    public Instant getTime() {
        return this.time;
    }
	  
    public String toString() {
        if (this.time != null) {
            return Long.toString(this.time.toEpochMilli());
        }
        return null;
    }
}
