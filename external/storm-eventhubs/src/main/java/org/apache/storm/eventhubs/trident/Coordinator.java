/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.apache.storm.eventhubs.trident;

import org.apache.storm.trident.spout.IOpaquePartitionedTridentSpout;
import org.apache.storm.trident.spout.IPartitionedTridentSpout;
import org.apache.storm.eventhubs.core.EventHubConfig;
import org.apache.storm.eventhubs.core.Partition;
import org.apache.storm.eventhubs.core.Partitions;

public class Coordinator implements IPartitionedTridentSpout.Coordinator<Partitions>,
        IOpaquePartitionedTridentSpout.Coordinator<Partitions> {
    private final EventHubConfig spoutConfig;
    private Partitions partitions;
  
    public Coordinator(EventHubConfig spoutConfig) {
        this.spoutConfig = spoutConfig;
        
        for (int i = 0; i < this.spoutConfig.getPartitionCount(); i++) {
        	this.partitions.addPartition(new Partition(String.valueOf(i)));
        }
    }

    @Override
    public void close() {
    }

    @Override
    public Partitions getPartitionsForBatch() {
        return this.partitions;
    }

    @Override
    public boolean isReady(long txid) {
        return true;
    }
}
