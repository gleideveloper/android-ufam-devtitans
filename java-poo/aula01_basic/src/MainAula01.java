class MainAula01 {
    public static void main(String[] args) {
        Aviao baraoVermelho = new Aviao("Fooker DR1", "Freitherr");
        baraoVermelho.imprimirOk();

        baraoVermelho.setMotor(false);
        baraoVermelho.setAltura(0.0f);

        System.out.println(
            "Modelo: " + baraoVermelho.getModelo() +
            "\nId: " + baraoVermelho.getIdentificador() +
            "\nStatus Motor: " + baraoVermelho.getModelo() +
            "\nAltura: " + baraoVermelho.getAltura()
        );

        System.out.println("----------------------------");
        baraoVermelho.ligarMotor();
        baraoVermelho.imprimeEstadoMotor();
        baraoVermelho.desligarMotor();
        baraoVermelho.imprimeEstadoMotor();

        System.out.println("----------------------------");
        baraoVermelho.ligarMotor();
        baraoVermelho.imprimeEstadoMotor();
        baraoVermelho.acelerar();
        baraoVermelho.acelerar();
        baraoVermelho.acelerar();
        baraoVermelho.acelerar();
        baraoVermelho.acelerar();
        baraoVermelho.desacelerar();
        baraoVermelho.desacelerar();
        baraoVermelho.desacelerar();
        baraoVermelho.desacelerar();
        baraoVermelho.desacelerar();
        baraoVermelho.imprimeEstadoMotor();
    }
}