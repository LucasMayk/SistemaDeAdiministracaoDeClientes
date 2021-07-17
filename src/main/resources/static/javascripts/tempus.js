var Tempus = Tempus || {};

Tempus.MaskMoney = (function() {
	
	// Função construtora para inicialização - Máscara renda familiar.
	function MaskMoney() {
		this.decimal = $('.js-decimal');
	}
	
	// Funções para executar o comportamento - Máscara renda familiar.
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
	}
	
	return MaskMoney;
	
}());

//Máscara do CPF.
Tempus.MaskCpf = (function() {
	function MaskCpf() {
		this.inputCpf = $('.js-cpf');
	}
	
	MaskCpf.prototype.enable = function() {
		this.inputCpf.mask('00000000000');
	}
	
	return MaskCpf;
}());

//Máscara de Data.
Tempus.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		})
	}
	
	return MaskDate;
	
}());

$(function() {
	// Máscara reda familiar.
	var maskMoney = new Tempus.MaskMoney();
	maskMoney.enable();
	
	// Máscara de CPF.
	var maskCpf = new Tempus.MaskCpf();
	maskCpf.enable();
	
	// Máscara de Data.
	var maskDate = new Tempus.MaskDate();
	maskDate.enable();
});