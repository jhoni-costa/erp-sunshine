package br.com.jhonicosta.erp.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.jhonicosta.erp.domain.Fornecedor;
import br.com.jhonicosta.erp.dto.FornecedorDTO;
import br.com.jhonicosta.erp.repositories.FornecedorRepository;
import br.com.jhonicosta.erp.resources.exceptions.FieldMessage;

public class FornecedorUpdateValidator implements ConstraintValidator<FornecedorUpdate, FornecedorDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private FornecedorRepository fRepository;

	@Override
	public void initialize(FornecedorUpdate ann) {
	}

	@Override
	public boolean isValid(FornecedorDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Fornecedor aux = fRepository.findByEmail(objDto.getEmail());

		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessege()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}