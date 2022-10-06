package br.com.hotel.controller.form;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.hotel.model.Hospedagem;
import br.com.hotel.regras.Valor;

public class HospedagemCheckoutForm {
	private Long idCheckin;
	private LocalDateTime dataHoraCheckOut;
	public Long getIdCheckin() {
		return idCheckin;
	}
	public void setIdCheckin(Long idCheckin) {
		this.idCheckin = idCheckin;
	}
	public LocalDateTime getDataHoraCheckOut() {
		return dataHoraCheckOut;
	}
	public void setDataHoraCheckOut(LocalDateTime dataHoraCheckOut) {
		this.dataHoraCheckOut = dataHoraCheckOut;
	}
	
	public Hospedagem converter(Hospedagem checkin) {
		double valorTotal = 0;
		Long diarias;
		int qtdDiariasNormais = 0;
		int qtdDiariasFDS = 0;
		
		checkin.setDataHoraCheckout(dataHoraCheckOut);
		
		// Descobrir quantas diárias o hóspede teve
		LocalDate dataInicial =  checkin.getDataHoraChecking().toLocalDate(); 
		LocalDate dataFinal = checkin.getDataHoraCheckout().toLocalDate();
		Duration dur = Duration.between(checkin.getDataHoraChecking(), checkin.getDataHoraCheckout());
		diarias = dur.toDays();
		
		// Percorrer cada dia para verificar se é dia de semana ou final de semana
		for (int i = 0; i < diarias; i++) {
			dataInicial = checkin.getDataHoraChecking().toLocalDate().plusDays(i);
			
			if((dataInicial.getDayOfWeek() == DayOfWeek.SATURDAY) || (dataInicial.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				qtdDiariasFDS++;
				valorTotal = valorTotal + Valor.VALOR_DIARIA_FINAL_SEMANA;
				if(checkin.isVagaGaragem()) {
					valorTotal = valorTotal + Valor.VALOR_GARAGEM_FINAL_SEMANA;
				}
			}else {
				qtdDiariasNormais++;
				valorTotal = valorTotal + Valor.VALOR_DIARIA_SEGUNDA_SEXTA;
				if(checkin.isVagaGaragem()) {
					valorTotal = valorTotal + Valor.VALOR_GARAGEM_SEGUNDA_SEXTA;
				}
			}
		}
		
		// Verificar se o checkout passou das 16:30
		boolean acrescentarDiaria = false;
		if(checkin.getDataHoraCheckout().getHour() >= 16) {
			if(checkin.getDataHoraCheckout().getHour() == 16) {
				if(checkin.getDataHoraCheckout().getMinute() > 30) {
					acrescentarDiaria = true;
				}
			} else {
				acrescentarDiaria = true;
			}
		}
		
		if(acrescentarDiaria) {
			dataInicial = checkin.getDataHoraCheckout().toLocalDate().plusDays(1);
			if((dataInicial.getDayOfWeek() == DayOfWeek.SATURDAY) || (dataInicial.getDayOfWeek() == DayOfWeek.SUNDAY)) {
				qtdDiariasFDS++;
				valorTotal = valorTotal + Valor.VALOR_DIARIA_FINAL_SEMANA;
				if(checkin.isVagaGaragem()) {
					valorTotal = valorTotal + Valor.VALOR_GARAGEM_FINAL_SEMANA;
				}
			}else {
				qtdDiariasNormais++;
				valorTotal = valorTotal + Valor.VALOR_DIARIA_SEGUNDA_SEXTA;
				if(checkin.isVagaGaragem()) {
					valorTotal = valorTotal + Valor.VALOR_GARAGEM_SEGUNDA_SEXTA;
				}
			}
		}
		
		checkin.setValorTotal(valorTotal);
		checkin.setFinalizada(true);
		
		String extrato = "Para o cálculo foram considerados:\n"+
						 "Data/Hora Check-in: "+checkin.getDataHoraChecking()+"\n"+
						 "Data/Hora Check-out: "+checkin.getDataHoraCheckout()+"\n"+
						 "Garagem: "+checkin.isVagaGaragem()+"\n"+
		                 "Dias normais: "+qtdDiariasNormais+"\n"+
		                 "Dias FDS: "+qtdDiariasFDS;
		checkin.setExtrato(extrato);
		
		return checkin;
	}
}
