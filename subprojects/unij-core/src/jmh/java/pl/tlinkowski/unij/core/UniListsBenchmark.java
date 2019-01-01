/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.tlinkowski.unij.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;

/**
 * @author Tomasz Linkowski
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class UniListsBenchmark {

  //region of()
  @Benchmark
  public List<?> listOf() {
    return List.of();
  }

  @Benchmark
  public List<?> uniListsOf() {
    return UniLists.of();
  }
  //endregion

  //region of(n=1)
  @Benchmark
  public List<?> listOf1() {
    return List.of(1);
  }

  @Benchmark
  public List<?> uniListsOf1() {
    return UniLists.of(1);
  }
  //endregion

  //region of(n=2)
  @Benchmark
  public List<?> listOf2() {
    return List.of(1, 2);
  }

  @Benchmark
  public List<?> uniListsOf2() {
    return UniLists.of(1, 2);
  }
  //endregion

  //region of(n=3)
  @Benchmark
  public List<?> listOf3() {
    return List.of(1, 2, 3);
  }

  @Benchmark
  public List<?> uniListsOf3() {
    return UniLists.of(1, 2, 3);
  }
  //endregion

  //region of(n=10)
  @Benchmark
  public List<?> listOf10() {
    return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  }

  @Benchmark
  public List<?> uniListsOf10() {
    return UniLists.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  }
  //endregion

  //region of(n=20)
  @Benchmark
  public List<?> listOf20() {
    return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  }

  @Benchmark
  public List<?> uniListsOf20() {
    return UniLists.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  }
  //endregion
}
