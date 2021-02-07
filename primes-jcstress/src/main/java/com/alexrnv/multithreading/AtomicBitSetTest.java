/*
 * Copyright (c) 2017, Red Hat Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.alexrnv.multithreading;

import com.alexrnv.multithreading.util.AtomicBitSet;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IIII_Result;
import org.openjdk.jcstress.infra.results.II_Result;
import org.openjdk.jcstress.infra.results.LLLL_Result;

@JCStressTest
@Outcome(id = "true, true, true, true", expect = Expect.ACCEPTABLE, desc = "Expected outcome. All bits are set without overriding each other")
@State
public class AtomicBitSetTest {

    AtomicBitSet atomicBitSet = new AtomicBitSet(8);

    @Actor
    public void actor1() {
        atomicBitSet.set(0);
    }

    @Actor
    public void actor2() {
        atomicBitSet.set(1);
    }

    @Actor
    public void actor3() {
        atomicBitSet.set(2);
    }

    @Actor
    public void actor4() {
        atomicBitSet.set(3);
    }

    @Arbiter
    public void arbiter(LLLL_Result s) {
        s.r1 = atomicBitSet.get(0);
        s.r2 = atomicBitSet.get(1);
        s.r3 = atomicBitSet.get(2);
        s.r4 = atomicBitSet.get(3);
    }

}
