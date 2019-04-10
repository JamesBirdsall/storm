package org.apache.storm.eventhubs.core;

public class OffsetFilter implements IEventFilter {
    String offset = null;

    public OffsetFilter(String offset) {
        this.offset = offset;
    }
	  
    public String getOffset() {
      return this.offset;
    }
	  
    public String toString() {
        if (this.offset != null) {
            return this.offset;
        }
        return null;
    }
}
