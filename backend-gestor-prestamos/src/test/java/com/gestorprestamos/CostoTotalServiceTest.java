package com.gestorprestamos;

import com.gestorprestamos.services.CostoTotalService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CostoTotalServiceTest {

    private final CostoTotalService costoTotalService = new CostoTotalService();

    @Test
    void whenMonthlyQuotaCalculate_thenReturnsCorrectValue() {
        // Given
        double monto = 100000;
        int plazo = 10; // 10 años
        double tasaInteres = 5; // 5%

        // When
        double cuotaMensual = costoTotalService.monthlyQuotaCalculate(monto, plazo, tasaInteres);

        // Then
        assertThat(cuotaMensual).isGreaterThan(0); // Verifica que la cuota mensual sea mayor que 0
    }

    @Test
    void whenCreditInsuranceCalculate_thenReturnsCorrectValue() {
        // Given
        double monto = 100000;

        // When
        double seguroDesgravamen = costoTotalService.creditInsuranceCalculate(monto);

        // Then
        assertThat(Math.round(seguroDesgravamen)).isEqualTo(30); // 0.03% de 100000 es 30
    }

    @Test
    void whenFireInsuranceCalculate_thenReturnsCorrectValue() {
        // When
        double seguroIncendio = costoTotalService.fireInsuranceCalculate();

        // Then
        assertThat(seguroIncendio).isEqualTo(20000); // El valor fijo del seguro de incendio
    }

    @Test
    void whenAdministrationCommissionCalculate_thenReturnsCorrectValue() {
        // Given
        double monto = 100000;

        // When
        double comisionAdministracion = costoTotalService.administrationCommissionCalculate(monto);

        // Then
        assertThat(comisionAdministracion).isEqualTo(1000); // 1% de 100000 es 1000
    }

    @Test
    void whenTotalCostCalculate_thenReturnsCorrectValue() {
        // Given
        double monto = 100000;
        int plazo = 10; // 10 años
        double tasaInteres = 5; // 5%

        // When
        double costoTotal = costoTotalService.totalCostCalculate(monto, plazo, tasaInteres);

        // Then
        assertThat(costoTotal).isGreaterThan(0); // Verifica que el costo total sea mayor que 0
    }
}
