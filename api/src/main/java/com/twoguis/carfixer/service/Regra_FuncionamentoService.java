    package com.twoguis.carfixer.service;

    import java.util.ArrayList;
    import java.util.List;
    import org.jdbi.v3.core.Jdbi;
    import org.springframework.stereotype.Service;

    import com.twoguis.carfixer.dao.Regra_FuncionamentoDao;
    import com.twoguis.carfixer.model.Regra_Funcionamento;

    @Service
    public class Regra_FuncionamentoService {

        private final Regra_FuncionamentoDao regra_funcionamentoDao;

        public Regra_FuncionamentoService(Jdbi jdbi) {
            this.regra_funcionamentoDao = jdbi.onDemand(Regra_FuncionamentoDao.class);
        }

    public Regra_Funcionamento insert(Regra_Funcionamento regra_funcionamento) {
        List<Integer> diasInt = regra_funcionamento.getDiasInt();

        if (diasInt != null && !diasInt.isEmpty()) {
            StringBuilder diasBuilder = new StringBuilder();

            for (Integer dia : diasInt) {
                if (dia >= 0 && dia <= 9) { // Verifica se o número é válido (0 a 9)
                    diasBuilder.append(dia);
                }
            }

            String dias = diasBuilder.toString();
            regra_funcionamento.setDias(dias);
        }

        int id_regra_funcionamento = regra_funcionamentoDao.insert(regra_funcionamento);
        regra_funcionamento.setId(id_regra_funcionamento);
        return regra_funcionamento;
    }


    public Regra_Funcionamento getById(int id) {
        Regra_Funcionamento regra_funcionamento = regra_funcionamentoDao.getById(id);

        if (regra_funcionamento != null) {
            String dias = regra_funcionamento.getDias();
            List<Integer> diasInt = new ArrayList<>();

            for (int i = 0; i < dias.length(); i++) {
                char c = dias.charAt(i);
                int intValue = Character.getNumericValue(c);
                if (intValue >= 0 && intValue <= 9) { // Verifica se o caractere é um dígito válido
                    diasInt.add(intValue);
                }
            }

            regra_funcionamento.setDiasInt(diasInt);

        // public List<Regra_Funcionamento> get() {
        // List<Regra_Funcionamento> regra_funcionamentos =
        // regra_funcionamentoDao.get();
        // List<Integer> diasInt = new ArrayList<>();

        // for (Regra_Funcionamento regra_funcionamento : regra_funcionamentos) {
        // char arr[] = regra_funcionamento.getDias().toCharArray(); // convert the
        // String object to array of char
        // for (char c : arr) {
        // diasInt.add(Integer.valueOf(c));
        // }
        // regra_funcionamento.setDiasInt(diasInt);
        // }

        // return regra_funcionamentos;
        // }

        public List<Regra_Funcionamento> get() {
            List<Regra_Funcionamento> regra_funcionamentos = regra_funcionamentoDao.get();

            for (Regra_Funcionamento regra_funcionamento : regra_funcionamentos) {
                String dias = regra_funcionamento.getDias();
                List<Integer> diasInt = new ArrayList<>();

                for (int i = 0; i < dias.length(); i++) {
                    char c = dias.charAt(i);
                    int intValue = Character.getNumericValue(c);
                    if (intValue >= 0 && intValue <= 9) { // Verifica se o caractere é um dígito válido
                        diasInt.add(intValue);
                    }
                }

                regra_funcionamento.setDiasInt(diasInt);
            }

            return regra_funcionamentos;

        }

        public Regra_Funcionamento getById(int id) {
            Regra_Funcionamento regra_funcionamento = regra_funcionamentoDao.getById(id);

            if (regra_funcionamento != null) {
                String dias = regra_funcionamento.getDias();
                List<Integer> diasInt = new ArrayList<>();

                for (int i = 0; i < dias.length(); i++) {
                    char c = dias.charAt(i);
                    int intValue = Character.getNumericValue(c);
                    if (intValue >= 0 && intValue <= 9) { // Verifica se o caractere é um dígito válido
                        diasInt.add(intValue);
                    }
                }

                regra_funcionamento.setDiasInt(diasInt);
            }

            return regra_funcionamento;
        }

        public void update(Regra_Funcionamento regra_funcionamento) {
            regra_funcionamentoDao.update(regra_funcionamento);
        }

        public void delete(int id) {
            regra_funcionamentoDao.delete(id);
        }
    }
}
