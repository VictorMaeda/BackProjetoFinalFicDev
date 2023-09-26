package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainApplication.class, args);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        // Execute todos os scripts SQL em uma única transação
        executeSQLScripts(jdbcTemplate);
    }
    @Transactional
    private static void executeSQLScripts(JdbcTemplate jdbcTemplate) {
        // Scripts SQL para inserir entidades na tabela public.enfermeiro
    	String[] enfermeiroScripts = {
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico1', 'Enfermeiro1', 'SP12345/21');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico2', 'Enfermeiro2', 'RJ67890/21');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico3', 'Enfermeiro3', 'MG54321/21');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico4', 'Enfermeiro4', 'RS98765/21');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico5', 'Enfermeiro5', 'SP54321/22');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico6', 'Enfermeiro6', 'RJ98765/22');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico7', 'Enfermeiro7', 'MG12345/22');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico8', 'Enfermeiro8', 'RS54321/23');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico9', 'Enfermeiro9', 'SP98765/23');",
    		    "INSERT INTO public.enfermeiro (enfermeiro_tecnico, nome, coren) VALUES ('Tecnico10', 'Enfermeiro10', 'RJ54321/24');"
    		};


        // Scripts SQL para inserir entidades na tabela public.plantao
        String[] plantaoScripts = {
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-01', '08:00:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-02', '14:30:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-03', '10:15:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-04', '20:45:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-05', '12:00:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-06', '16:30:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-07', '09:45:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-08', '18:15:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-09', '07:30:00');",
            "INSERT INTO public.plantao (dia, horario) VALUES ('2023-09-10', '13:45:00');"
        };
        		String[] relacionamentoScripts = {
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (1, 1);", // Enfermeiro1 e Plantao1
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (2, 2);", // Enfermeiro2 e Plantao2
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (3, 3);", // Enfermeiro3 e Plantao3
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (4, 4);", // Enfermeiro4 e Plantao4
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (5, 5);", // Enfermeiro5 e Plantao5
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (6, 6);", // Enfermeiro6 e Plantao6
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (7, 7);", // Enfermeiro7 e Plantao7
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (8, 8);", // Enfermeiro8 e Plantao8
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (9, 9);", // Enfermeiro9 e Plantao9
        			    "INSERT INTO public.enfermeiro_plantao (enfermeiro_id_enfermeiro, plantao_id_plantao) VALUES (10, 10);", // Enfermeiro10 e Plantao10
        	};
        

        // Execute os scripts SQL
        for (String script : enfermeiroScripts) {
           jdbcTemplate.execute(script);
        }
        for (String script : plantaoScripts) {
            jdbcTemplate.execute(script);
        }
        for (String script : relacionamentoScripts) {
            jdbcTemplate.execute(script);
        }

    }
}
