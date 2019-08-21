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

package pl.tlinkowski.unij.test.service.collect

import spock.lang.Shared
import spock.lang.Specification

import pl.tlinkowski.unij.service.api.collect.UnmodifiableMapFactory

/**
 * Base specification for {@link UnmodifiableMapFactory}.
 *
 * @author Tomasz Linkowski
 */
abstract class AbstractUnmodifiableMapFactorySpec extends Specification {

  @Shared
  protected UnmodifiableMapFactory factory

  def "proper service is registered"() {
    when:
      def registered = ServiceLoader.load(UnmodifiableMapFactory).first()
    then:
      registered.class == factory.class
  }

  //region COLLECTOR
  def "collector(keyMapper,valueMapper)"(Map<String, Integer> map) {
    given:
      def entryStream = map.entrySet().stream()
      def collector = factory.collector({ it.getKey() }, { it.getValue() })
    expect:
      entryStream.collect(collector) == map
    where:
      map                      | _
      [:]                      | _
      ["a": 1]                 | _
      ["a": 1, "b": 2]         | _
      ["a": 1, "b": 2, "c": 3] | _
  }

  def "collector(keyMapper,valueMapper,mergeFunction)"(List<Map<String, Integer>> maps, Map<String, Integer> merged) {
    given:
      def entryStream = maps.stream().flatMap { it.entrySet().stream() }
      def collector = factory.collector({ it.getKey() }, { it.getValue() }, { l, r -> l + r })
    expect:
      entryStream.collect(collector) == merged
    where:
      maps                         | merged
      []                           | [:]
      [["a": 1]]                   | ["a": 1]
      [["a": 1], ["b": 2]]         | ["a": 1, "b": 2]
      [["a": 1, "b": 2], ["b": 3]] | ["a": 1, "b": 5]
  }

  def "collector(key,value) throws on merge conflict"(List<Map<String, Integer>> maps) {
    given:
      def entryStream = maps.stream().flatMap { it.entrySet().stream() }
      def collector = factory.collector({ it.getKey() }, { it.getValue() })
    when:
      entryStream.collect(collector)
    then:
      thrown(IllegalStateException.class)
    where:
      maps                         | _
      [["a": 1, "b": 2], ["b": 3]] | _
  }
  //endregion

  def "copyOf"(Map<String, Integer> map) {
    expect:
      factory.copyOf(map) == Map.copyOf(map)
    where:
      map                      | _
      [:]                      | _
      ["a": 1]                 | _
      ["a": 1, "b": 2]         | _
      ["a": 1, "b": 2, "c": 3] | _
  }

  // region ENTRIES
  def "ofEntries(...)"() {
    given:
      def expected = Map.ofEntries(
              Map.entry("a", 1),
              Map.entry("b", 2),
              Map.entry("c", 3)
      )
    when:
      def actual = factory.ofEntries(
              factory.entry("a", 1),
              factory.entry("b", 2),
              factory.entry("c", 3)
      )
    then:
      actual == expected
  }

  def "entry"() {
    expect:
      factory.entry("a", 1) == Map.entry("a", 1)
  }
  //endregion

  def "of(n=0)"() {
    expect:
      factory.of() == Map.of()
  }

  def "of(n=1)"() {
    expect:
      factory.of("a", 1) == Map.of("a", 1)
  }

  def "of(n=2)"() {
    expect:
      factory.of("a", 1, "b", 2) == Map.of("a", 1, "b", 2)
  }

  def "of(n=3)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3) ==
              Map.of("a", 1, "b", 2, "c", 3)
  }

  def "of(n=4)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4)
  }

  def "of(n=5)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5)
  }

  def "of(n=6)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6)
  }

  def "of(n=7)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7)
  }

  def "of(n=8)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8)
  }

  def "of(n=9)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9)
  }

  def "of(n=10)"() {
    expect:
      factory.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9, "j", 10) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9, "j", 10)
  }
}
