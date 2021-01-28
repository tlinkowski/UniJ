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
package pl.tlinkowski.unij.api;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import pl.tlinkowski.unij.service.api.collect.*;
import pl.tlinkowski.unij.service.api.misc.MiscellaneousApiProvider;

/**
 * @author Tomasz Linkowski
 */
@UtilityClass
final class UniJ {

  @Getter(lazy = true)
  private static final UnmodifiableListFactory listFactory = UniJLoader.load(UnmodifiableListFactory.class);
  @Getter(lazy = true)
  private static final UnmodifiableSetFactory setFactory = UniJLoader.load(UnmodifiableSetFactory.class);
  @Getter(lazy = true)
  private static final UnmodifiableMapFactory mapFactory = UniJLoader.load(UnmodifiableMapFactory.class);

  @Getter(lazy = true)
  private static final MiscellaneousApiProvider miscProvider = UniJLoader.load(MiscellaneousApiProvider.class);
}
