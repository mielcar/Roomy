package com.bu.roomy.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.hamcrest.Matchers.hasSize
import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BookingOptimizationControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    def 'Should return proper optimization for happy path'() {
        expect:
            mockMvc.perform(MockMvcRequestBuilders.get("/bookings/optimization")
                .param('economyRooms', '1')
                .param('premiumRooms', '10')
                .param('premiumThreshold', '100'))
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.result.economy.bookedRoomsAmount', is(1)))
                .andExpect(jsonPath('$.result.economy.totalValue', is(22d)))
                .andExpect(jsonPath('$.result.premium.bookedRoomsAmount', is(9)))
                .andExpect(jsonPath('$.result.premium.totalValue', is(1221d)))
    }

    def 'Should validate input params'() {
        expect:
            mockMvc.perform(MockMvcRequestBuilders.get("/bookings/optimization")
                .param('economyRooms', '-1')
                .param('premiumRooms', '-1')
                .param('premiumThreshold', '-1'))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath('$.errors', hasSize(3)))
                .andExpect(jsonPath('$.errors', containsInAnyOrder(
                    'Economy rooms amount can\'t be lower than 0!',
                    'Premium rooms amount can\'t be lower than 0!',
                    'Price threshold can\'t be lower than 0!'
                )))
    }
}
