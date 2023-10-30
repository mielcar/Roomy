package com.bu.roomy.domain

import spock.lang.Specification

class PriceTest extends Specification {

    def 'Should properly initialize CustomerPreferences with default currency'() {
        when:
            Price result = Price.withDefaultCurrency(69d)

        then:
            result.price == 69d
            result.currency == Currency.getInstance('EUR')
    }

    def 'Should not initialize CustomerPreferences with wrong value'() {
        when:
            Price.withDefaultCurrency(value)

        then:
            thrown(IllegalArgumentException)

        where:
            value << [-1, -0.5, 0]
    }
}
