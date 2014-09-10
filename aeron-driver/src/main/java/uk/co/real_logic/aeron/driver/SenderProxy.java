/*
 * Copyright 2014 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.aeron.driver;

import uk.co.real_logic.aeron.common.concurrent.AtomicCounter;
import uk.co.real_logic.aeron.driver.cmd.ClosePublicationCmd;
import uk.co.real_logic.aeron.driver.cmd.NewPublicationCmd;
import uk.co.real_logic.aeron.driver.cmd.RetransmitPublicationCmd;

import java.util.Queue;

/**
 * Proxy for offering into the Sender Thread's command queue.
 */
public class SenderProxy
{
    private final Queue<Object> commandQueue;
    private final AtomicCounter failCount;

    public SenderProxy(final Queue<Object> commandQueue, final AtomicCounter failCount)
    {
        this.commandQueue = commandQueue;
        this.failCount = failCount;
    }

    public void retransmit(final DriverPublication publication, final int termId, final int termOffset, final int length)
    {
        offerCommand(new RetransmitPublicationCmd(publication, termId, termOffset, length));
    }

    public void closePublication(final DriverPublication publication)
    {
        offerCommand(new ClosePublicationCmd(publication));
    }

    public void newPublication(final DriverPublication publication)
    {
        offerCommand(new NewPublicationCmd(publication));
    }

    private void offerCommand(Object cmd)
    {
        while (!commandQueue.offer(cmd))
        {
            failCount.orderedIncrement();
            Thread.yield();
        }
    }
}
