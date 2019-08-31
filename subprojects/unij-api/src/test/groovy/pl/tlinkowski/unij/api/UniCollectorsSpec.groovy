/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
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

package pl.tlinkowski.unij.api

import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * @author Tomasz Linkowski
 */
class UniCollectorsSpec extends Specification {

  def "toUnmodifiableList()"(List<Integer> list) {
    expect:
      list.stream().collect(UniCollectors.toUnmodifiableList()) == list
    where:
      list      | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

  def "toUnmodifiableSet()"(Set<Integer> set) {
    expect:
      set.stream().collect(UniCollectors.toUnmodifiableSet()) == set
    where:
      set       | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

  //region TO UNMODIFIABLE MAP (corresponds to COLLECTOR region of AbstractUnmodifiableMapFactorySpec)
  def "toUnmodifiableMap(keyMapper,valueMapper)"(Map<String, Integer> map) {
    given:
      def entryStream = map.entrySet().stream()
      def collector = UniCollectors.toUnmodifiableMap({ it.getKey() }, { it.getValue() })
    expect:
      entryStream.collect(collector) == map
    where:
      map                      | _
      [:]                      | _
      ["a": 1]                 | _
      ["a": 1, "b": 2]         | _
      ["a": 1, "b": 2, "c": 3] | _
  }

  def "toUnmodifiableMap(keyMapper,valueMapper,mergeFunction)"(List<Map<String, Integer>> maps,
          Map<String, Integer> merged) {
    given:
      def entryStream = maps.stream().flatMap { it.entrySet().stream() }
      def collector = UniCollectors.toUnmodifiableMap(
              { it.getKey() }, { it.getValue() }, { l, r -> l + r }
      )
    expect:
      entryStream.collect(collector) == merged
    where:
      maps                         | merged
      []                           | [:]
      [["a": 1]]                   | ["a": 1]
      [["a": 1], ["b": 2]]         | ["a": 1, "b": 2]
      [["a": 1, "b": 2], ["b": 3]] | ["a": 1, "b": 5]
  }
  //endregion

  //region MISCELLANEOUS (corresponds to COLLECTORS region of AbstractMiscellaneousApiProviderSpec)
  def "flatMapping(mapper,downstream)"(List<Integer> list, List<Integer> expected) {
    when:
      def collector = UniCollectors.flatMapping({ Stream.of(it, -it) }, Collectors.toList())
    then:
      list.stream().collect(collector) == expected
    where:
      list      | expected
      []        | []
      [1]       | [1, -1]
      [-1]      | [-1, 1]
      [1, 2]    | [1, -1, 2, -2]
      [1, 2, 3] | [1, -1, 2, -2, 3, -3]
  }

  def "filtering(predicate,downstream)"(List<Integer> list, List<Integer> expected) {
    when:
      def collector = UniCollectors.filtering({ it % 2 != 0 }, Collectors.toList())
    then:
      list.stream().collect(collector) == expected
    where:
      list      | expected
      []        | []
      [1]       | [1]
      [2]       | []
      [1, 2]    | [1]
      [1, 2, 3] | [1, 3]
  }
  //endregion
}
