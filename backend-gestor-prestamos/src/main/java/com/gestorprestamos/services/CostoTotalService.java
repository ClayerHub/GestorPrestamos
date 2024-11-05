package com.gestorprestamos.services;

import org.springframework.stereotype.Service;

@Service
public class CostoTotalService {

    public double monthlyQuotaCalculate(double monto, int plazo, double tasaInteres){
        double tasaMensual = tasaInteres /12 /100;
        int numeroPagos = plazo * 12;
        return monto * (tasaMensual * Math.pow(1 + tasaMensual, numeroPagos)) / (Math.pow(1 + tasaMensual, numeroPagos) - 1);
    }

    public double creditInsuranceCalculate(double monto){
        double tasaSeguroDesagravamen = 0.03 / 100;
        return monto * tasaSeguroDesagravamen;
    }

    public double fireInsuranceCalculate(){
        return 20000;
    }

    public double administrationCommissionCalculate(double monto){
        double tasaComision = 0.01;
        return monto * tasaComision;
    }

    public double totalCostCalculate(double monto, int plazo, double tasaInteres){
        double cuotaMensual = monthlyQuotaCalculate(monto, plazo, tasaInteres);
        double seguroDesgravamen = creditInsuranceCalculate(monto);
        double seguroIncendio = fireInsuranceCalculate();
        double comisionAdministracion = administrationCommissionCalculate(monto);

        double costoMensualTotal = cuotaMensual + seguroDesgravamen + seguroIncendio;
        double costoTotal = (costoMensualTotal * plazo * 12) + comisionAdministracion;

        return costoTotal;
    }
}
