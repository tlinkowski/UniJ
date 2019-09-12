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

import pl.tlinkowski.unij.api.*;
import pl.tlinkowski.unij.service.api.collect.*;
import pl.tlinkowski.unij.service.api.misc.MiscellaneousApiProvider;

/**
 * UniJ API for the end users.
 * <p>
 * The API consists of the following static utility classes: {@link UniLists}, {@link UniSets}, {@link UniMaps}, {@link
 * UniCollectors}.
 *
 * @author Tomasz Linkowski
 */
module pl.tlinkowski.unij.api {
  exports pl.tlinkowski.unij.api;

  requires pl.tlinkowski.unij.service.api;
  requires org.slf4j;

  requires static pl.tlinkowski.annotation.basic;
  requires static lombok;

  uses UnmodifiableListFactory;
  uses UnmodifiableSetFactory;
  uses UnmodifiableMapFactory;

  uses MiscellaneousApiProvider;
}
