/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2021 Tomasz Linkowski.
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
package pl.tlinkowski.unij.test.service.collect

import groovy.transform.PackageScope

import java.util.stream.Collector

/**
 * Helper class for the {@link List}/{@link Set} specs.
 *
 * Includes data pipes: http://spockframework.org/spock/docs/1.3/data_driven_testing.html#_data_pipes
 *
 * @author Tomasz Linkowski
 */
@PackageScope
class UnmodifiableCollectionSpecHelper {

  //region DATA PIPES
  static List<List<Integer>> lists() {
    [
            [],
            [1],
            [1, 2],
            [1, 2, 3],
            [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
            [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
    ]
  }

  static List<List<Integer>> listsWithNull() {
    [
            [null],
            [1, null],
            [1, 2, null],
            [1, 2, 3, 4, 5, 6, 7, 8, 9, null],
            [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, null]
    ]
  }
  //endregion

  //region ARGS
  static List<Integer> args(int size) {
    size == 0 ? [] : 1..size
  }

  static List<Integer> argsWithNull(int size, int nullIndex) {
    Integer[] args = args(size)
    args[nullIndex] = null
    args
  }
  //endregion

  //region COLLECT
  static <E, C extends Collection<E>> C collect(Collector<E, ?, C> collector, List<E> list) {
    list.stream().collect(collector)
  }
  //endregion
}
