package com.bu.roomy.domain

import spock.lang.Specification

class PaymentPreferenceTest extends Specification {

    def 'Should properly initialize PaymentPreference with default currency'() {
        when:
            PaymentPreference result = PaymentPreference.withDefaultCurrency(69d)

        then:
            result.value == 69d
            result.currency == Currency.getInstance('EUR')
    }

    def 'Should not initialize PaymentPreference with wrong value'() {
        when:
            PaymentPreference.withDefaultCurrency(value)

        then:
            thrown(IllegalArgumentException)

        where:
            value << [-1, -0.5, 0]
    }
}
