package com.schema.multitenancy.config;

import java.util.Optional;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.schema.multitenancy.util.Constant;


@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		return Optional.ofNullable(TenantContext.getCurrentTenant())
				.orElse(Constant.DEFAULT_TENANT_ID);
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}