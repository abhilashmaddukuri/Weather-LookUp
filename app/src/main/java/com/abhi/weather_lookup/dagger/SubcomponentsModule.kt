package com.abhi.weather_lookup.dagger

import dagger.Module

/**
 * The "subcomponents" attribute in the @Module annotation tells Dagger what
 * Subcomponents are children of the Component this module is included in.
 */
@Module(subcomponents = [WeatherInfoComponent::class])
class SubcomponentsModule {}