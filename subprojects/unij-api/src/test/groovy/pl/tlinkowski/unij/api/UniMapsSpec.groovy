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

/**
 * @author Tomasz Linkowski
 */
final class UniMapsSpec extends Specification {

  def "copyOf"(Map<String, Integer> map) {
    expect:
      UniMaps.copyOf(map) == Map.copyOf(map)
    where:
      map                      | _
      [:]                      | _
      ["a": 1]                 | _
      ["a": 1, "b": 2]         | _
      ["a": 1, "b": 2, "c": 3] | _
  }

  // region ENTRIES (corresponds to ENTRIES region of AbstractUnmodifiableMapFactorySpec)
  def "ofEntries(...)"() {
    given:
      def expected = Map.ofEntries(
              Map.entry("a", 1),
              Map.entry("b", 2),
              Map.entry("c", 3)
      )
    when:
      def actual = UniMaps.ofEntries(
              UniMaps.entry("a", 1),
              UniMaps.entry("b", 2),
              UniMaps.entry("c", 3)
      )
    then:
      actual == expected
  }

  def "entry"() {
    expect:
      UniMaps.entry("a", 1) == Map.entry("a", 1)
  }
  //endregion

  def "of(n=0)"() {
    expect:
      UniMaps.of() == Map.of()
  }

  def "of(n=1)"() {
    expect:
      UniMaps.of("a", 1) == Map.of("a", 1)
  }

  def "of(n=2)"() {
    expect:
      UniMaps.of("a", 1, "b", 2) == Map.of("a", 1, "b", 2)
  }

  def "of(n=3)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3) ==
              Map.of("a", 1, "b", 2, "c", 3)
  }

  def "of(n=4)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4)
  }

  def "of(n=5)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5)
  }

  def "of(n=6)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6)
  }

  def "of(n=7)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7)
  }

  def "of(n=8)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8)
  }

  def "of(n=9)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9)
  }

  def "of(n=10)"() {
    expect:
      UniMaps.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9, "j", 10) ==
              Map.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5, "f", 6, "g", 7, "h", 8, "i", 9, "j", 10)
  }
}
