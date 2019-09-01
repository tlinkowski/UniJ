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

import static pl.tlinkowski.unij.test.service.collect.UnmodifiableMapSpecHelper.*
import spock.lang.Shared
import spock.lang.Specification

import java.util.stream.Collectors

import pl.tlinkowski.unij.service.api.collect.UnmodifiableMapFactory

/**
 * Base specification for {@link UnmodifiableMapFactory}.
 *
 * @author Tomasz Linkowski
 */
abstract class AbstractUnmodifiableMapFactorySpec extends Specification {

  @Shared
  protected UnmodifiableMapFactory factory

  def "proper service is registered on classpath"() {
    when:
      def registered = ServiceLoader.load(UnmodifiableMapFactory).first()
    then:
      registered.class == factory.class
  }

  //region STANDARD CONTRACT
  def "collector(keyMapper,valueMapper)"(Map<String, Integer> map) {
    expect:
      collect(collector2(factory.&collector), map) == collect(collector2(Collectors.&toUnmodifiableMap), map)
    where:
      map << maps()
  }

  def "collector(keyMapper,valueMapper,mergeFunction)"(List<Map<String, Integer>> maps) {
    expect:
      collect(collector3(factory.&collector), maps) == collect(collector3(Collectors.&toUnmodifiableMap), maps)
    where:
      maps                   | _
      []                     | _
      [[a: 1]]               | _
      [[a: 1], [b: 2]]       | _
      [[a: 1, b: 2], [b: 3]] | _
  }

  def "copyOf"(Map<String, Integer> map) {
    expect:
      factory.copyOf(map) == Map.copyOf(map)
    where:
      map << maps()
  }

  def "ofEntries"(Map<String, Integer> map) {
    given:
      Map.Entry<String, Integer>[] entries = map.entrySet()
    expect:
      factory.ofEntries(entries) == Map.ofEntries(entries)
    where:
      map << maps()
  }

  def "entry"() {
    expect:
      factory.entry("a", 1) == Map.entry("a", 1)
  }

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
  //endregion

  //region NULLABILITY CONTRACT
  def "collector(keyMapper,valueMapper) throws NPE"(Map<String, Integer> map) {
    when:
      collect(collector2(factory.&collector), map)
    then:
      thrown(NullPointerException)
    where:
      map << mapsWithNull()
  }

  def "collector(keyMapper,valueMapper,mergeFunction) throws NPE"(Map<String, Integer> map) {
    when:
      collect(collector3(factory.&collector), map)
    then:
      thrown(NullPointerException)
    where:
      map << mapsWithNull()
  }

  def "copyOf throws NPE"(Map<String, Integer> map) {
    when:
      factory.copyOf(map)
    then:
      thrown(NullPointerException)
    where:
      map << mapsWithNull()
  }

  def "ofEntries(...) throws NPE"(Map<String, Integer> map) {
    given:
      Map.Entry<String, Integer>[] entries = map.entrySet()
    when:
      factory.ofEntries(entries)
    then:
      thrown(NullPointerException)
    where:
      map << mapsWithNull()
  }

  def "of(n=1) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(1, nullIndex, target)
    when:
      factory.of(k(e[0]), v(e[0]))
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(1)
  }

  def "of(n=2) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(2, nullIndex, target)
    when:
      factory.of(k(e[0]), v(e[0]), k(e[1]), v(e[1]))
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(2)
  }

  def "of(n=3) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(3, nullIndex, target)
    when:
      factory.of(k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]))
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(3)
  }

  def "of(n=4) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(4, nullIndex, target)
    when:
      factory.of(k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]))
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(4)
  }

  def "of(n=5) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(5, nullIndex, target)
    when:
      factory.of(k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]), k(e[4]), v(e[4]))
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(5)
  }

  def "of(n=6) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(6, nullIndex, target)
    when:
      factory.of(
              k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]), k(e[4]), v(e[4]),
              k(e[5]), v(e[5])
      )
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(6)
  }

  def "of(n=7) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(7, nullIndex, target)
    when:
      factory.of(
              k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]), k(e[4]), v(e[4]),
              k(e[5]), v(e[5]), k(e[6]), v(e[6])
      )
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(7)
  }

  def "of(n=8) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(8, nullIndex, target)
    when:
      factory.of(
              k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]), k(e[4]), v(e[4]),
              k(e[5]), v(e[5]), k(e[6]), v(e[6]), k(e[7]), v(e[7])
      )
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(8)
  }

  def "of(n=9) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(9, nullIndex, target)
    when:
      factory.of(
              k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]), k(e[4]), v(e[4]),
              k(e[5]), v(e[5]), k(e[6]), v(e[6]), k(e[7]), v(e[7]), k(e[8]), v(e[8])
      )
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(9)
  }

  def "of(n=10) throws NPE"(int nullIndex, NullTarget target) {
    given:
      def e = entriesWithNull(10, nullIndex, target)
    when:
      factory.of(
              k(e[0]), v(e[0]), k(e[1]), v(e[1]), k(e[2]), v(e[2]), k(e[3]), v(e[3]), k(e[4]), v(e[4]),
              k(e[5]), v(e[5]), k(e[6]), v(e[6]), k(e[7]), v(e[7]), k(e[8]), v(e[8]), k(e[9]), v(e[9])
      )
    then:
      thrown(NullPointerException)
    where:
      [nullIndex, target] << combinationsWithNull(10)
  }
  //endregion

  //region CONSISTENCY CONTRACT
  def "of(n=0) has only one instance"(Map<String, Integer> map) {
    when:
      def actual = factory.of()
    then:
      map.is(actual)
    where:
      map               | _
      factory.of()      | _
      ofEntriesSized(0) | _
      copyOfSized(0)    | _
      collect2Sized(0)  | _
      collect3Sized(0)  | _
  }

  def "of(n=1) has only one type"(Map<String, Integer> map) {
    when:
      def actual = factory.of("a", 1)
    then:
      map == actual
      map.class == actual.class
    where:
      map                | _
      factory.of("a", 1) | _
      ofEntriesSized(1)  | _
      copyOfSized(1)     | _
      collect2Sized(1)   | _
      collect3Sized(1)   | _
  }

  def "of(n=2) has only one type"(Map<String, Integer> map) {
    when:
      def actual = factory.of("a", 1, "b", 2)
    then:
      map == actual
      map.class == actual.class
    where:
      map                        | _
      factory.of("a", 1, "b", 2) | _
      ofEntriesSized(2)          | _
      copyOfSized(2)             | _
      collect2Sized(2)           | _
      collect3Sized(2)           | _
  }

  def "of(n=3) has only one type"(Map<String, Integer> map) {
    when:
      def actual = factory.of("a", 1, "b", 2, "c", 3)
    then:
      map == actual
      map.class == actual.class
    where:
      map                                | _
      factory.of("a", 1, "b", 2, "c", 3) | _
      ofEntriesSized(3)                  | _
      copyOfSized(3)                     | _
      collect2Sized(3)                   | _
      collect3Sized(3)                   | _
  }

  private Map<String, Integer> ofEntriesSized(int size) {
    factory.ofEntries(entryArray(size))
  }

  private Map<String, Integer> copyOfSized(int size) {
    factory.copyOf(Map.ofEntries(entryArray(size)))
  }

  private Map<String, Integer> collect2Sized(int size) {
    entries(size).stream().collect(collector2(factory.&collector))
  }

  private Map<String, Integer> collect3Sized(int size) {
    entries(size).stream().collect(collector3(factory.&collector))
  }
  //endregion

  //region DUPLICATION CONTRACT
  def "collector(key,value) throws on duplicates"(List<Map<String, Integer>> maps) {
    when:
      collect(collector2(factory.&collector), maps)
    then:
      Exception e = thrown()
      isDuplicateException(e)
    where:
      maps                   | _
      [[a: 1, b: 2], [b: 3]] | _
  }

  def "ofEntries throws on duplicates"(List<Map<String, Integer>> maps) {
    given:
      Map.Entry<String, Integer>[] entries = maps.collectMany { it.entrySet() }
    when:
      factory.ofEntries(entries)
    then:
      Exception e = thrown()
      isDuplicateException(e)
    where:
      maps                   | _
      [[a: 1, b: 2], [b: 3]] | _
  }

  def "of(n=2) throws on duplicates"() {
    when:
      factory.of("a", 1, "a", 2)
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=3) throws on duplicates"() {
    when:
      factory.of("a", 1, "b", 2, "a", 3)
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=4) throws on duplicates"() {
    when:
      factory.of("a", 1, "b", 2, "a", 3, "d", 4)
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=5) throws on duplicates"() {
    when:
      factory.of("a", 1, "b", 2, "a", 3, "d", 4, "e", 5)
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=6) throws on duplicates"() {
    when:
      factory.of(
              "a", 1, "b", 2, "c", 3, "a", 4, "e", 5,
              "f", 6
      )
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=7) throws on duplicates"() {
    when:
      factory.of(
              "a", 1, "b", 2, "c", 3, "b", 4, "e", 5,
              "f", 6, "g", 7
      )
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=8) throws on duplicates"() {
    when:
      factory.of(
              "a", 1, "b", 2, "c", 3, "b", 4, "e", 5,
              "f", 6, "g", 7, "h", 8
      )
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=9) throws on duplicates"() {
    when:
      factory.of(
              "a", 1, "b", 2, "c", 3, "d", 4, "b", 5,
              "f", 6, "g", 7, "h", 8, "i", 9
      )
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  def "of(n=10) throws on duplicates"() {
    when:
      factory.of(
              "a", 1, "b", 2, "c", 3, "d", 4, "b", 5,
              "f", 6, "g", 7, "h", 8, "i", 9, "j", 10
      )
    then:
      Exception e = thrown()
      isDuplicateException(e)
  }

  private static boolean isDuplicateException(Exception e) {
    (e instanceof IllegalArgumentException || e instanceof IllegalStateException) && e.message.contains("key")
  }
  //endregion
}
