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

import pl.tlinkowski.unij.service.collect.api.UnmodifiableSetFactory

/**
 * Base specification for {@link UnmodifiableSetFactory}.
 *
 * @author Tomasz Linkowski
 */
abstract class AbstractUnmodifiableSetFactorySpec extends Specification {

  @Shared
  protected UnmodifiableSetFactory factory

  def "proper service is registered"() {
    when:
      def registered = ServiceLoader.load(UnmodifiableSetFactory).first()
    then:
      registered.class == factory.class
  }

  def "collector"(Set<Integer> set) {
    expect:
      set.stream().collect(factory.collector()) == set
    where:
      set       | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

  def "copyOf"(Set<Integer> set) {
    expect:
      factory.copyOf(set) == Set.copyOf(set)
    where:
      set       | _
      []        | _
      [1]       | _
      [1, 2]    | _
      [1, 2, 3] | _
  }

  def "of(n=0)"() {
    expect:
      factory.of() == Set.of()
  }

  def "of(n=1)"() {
    expect:
      factory.of(1) == Set.of(1)
  }

  def "of(n=2)"() {
    expect:
      factory.of(1, 2) == Set.of(1, 2)
  }

  def "of(n=3)"() {
    expect:
      factory.of(1, 2, 3) == Set.of(1, 2, 3)
  }

  def "of(n=4)"() {
    expect:
      factory.of(1, 2, 3, 4) == Set.of(1, 2, 3, 4)
  }

  def "of(n=5)"() {
    expect:
      factory.of(1, 2, 3, 4, 5) == Set.of(1, 2, 3, 4, 5)
  }

  def "of(n=6)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6) == Set.of(1, 2, 3, 4, 5, 6)
  }

  def "of(n=7)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7) == Set.of(1, 2, 3, 4, 5, 6, 7)
  }

  def "of(n=8)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8) == Set.of(1, 2, 3, 4, 5, 6, 7, 8)
  }

  def "of(n=9)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9) == Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
  }

  def "of(n=10)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) == Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  }

  def "of(...)"() {
    expect:
      factory.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11) == Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
  }
}
