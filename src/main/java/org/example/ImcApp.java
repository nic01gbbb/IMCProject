package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImcApp extends JFrame {

    private JTextField textFieldPeso;
    private JTextField textFieldAltura;
    private JLabel labelResultado;

    public ImcApp() {
        setTitle("Calculadora de IMC");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Painel principal com GridBagLayout para organizar os componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Label e campo para Peso
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Peso (Kg): "), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        textFieldPeso = new JTextField(10);
        panel.add(textFieldPeso, gbc);

        // Label e campo para Altura
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Altura (Cm): "), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        textFieldAltura = new JTextField(10);
        panel.add(textFieldAltura, gbc);

        // Botão para calcular IMC
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton buttonCalcular = new JButton("Calcular IMC");
        panel.add(buttonCalcular, gbc);

        // Label para resultado
        gbc.gridy = 3;
        labelResultado = new JLabel("Informe seu peso e altura.");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(labelResultado, gbc);

        add(panel);

        // Evento do botão calcular
        buttonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularImc();
            }
        });
    }

    private void calcularImc() {
        try {
            double peso = Double.parseDouble(textFieldPeso.getText().replace(",", "."));
            double alturaCm = Double.parseDouble(textFieldAltura.getText().replace(",", "."));

            if(peso <= 0 || alturaCm <= 0){
                JOptionPane.showMessageDialog(this, "Peso e altura devem ser maiores que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double alturaM = alturaCm / 100.0;
            double imc = peso / (alturaM * alturaM);

            String classificacao;
            if (imc < 18.5) classificacao = "Abaixo do peso";
            else if (imc < 25) classificacao = "Peso Normal";
            else if (imc < 30) classificacao = "Sobrepeso";
            else if (imc < 35) classificacao = "Obesidade grau I";
            else if (imc < 40) classificacao = "Obesidade grau II";
            else classificacao = "Obesidade grau III";

            labelResultado.setText(String.format("Seu IMC é %.1f — %s", imc, classificacao));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para peso e altura.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImcApp app = new ImcApp();
            app.setVisible(true);
        });
    }
}
