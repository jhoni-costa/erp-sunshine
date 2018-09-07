package br.com.jhonicosta.erp.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.jhonicosta.erp.dto.FornecedorNewDTO;
import br.com.jhonicosta.erp.resources.exceptions.FieldMessage;
import br.com.jhonicosta.erp.services.validation.BR.BR;

public class FornecedorInsertValidator implements ConstraintValidator<FornecedorInsert, FornecedorNewDTO> {
	@Override
	public void initialize(FornecedorInsert ann) {
	}

	@Override
	public boolean isValid(FornecedorNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (BR.isValidCPF(objDto.getCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessege()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}