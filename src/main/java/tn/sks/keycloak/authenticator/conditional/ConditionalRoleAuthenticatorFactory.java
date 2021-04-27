package tn.sks.keycloak.authenticator.conditional;

import org.keycloak.Config;
import org.keycloak.models.AuthenticationExecutionModel;
import static org.keycloak.models.AuthenticationExecutionModel.Requirement;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticator;
import org.keycloak.authentication.authenticators.conditional.ConditionalAuthenticatorFactory;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

import java.util.Collections;
import java.util.List;

public class ConditionalRoleAuthenticatorFactory implements ConditionalAuthenticatorFactory {
    public static final String PROVIDER_ID = "conditional-user-not-in-role";
    protected static final String CONDITIONAL_USER_ROLE = "condUserNotInRole";
    
    private static List<ProviderConfigProperty> commonConfig;
    
    static {
        commonConfig = Collections.unmodifiableList(ProviderConfigurationBuilder.create()
                .property().name(CONDITIONAL_USER_ROLE).label("User Role")
                .helpText("Role the user should not be in to execute the flow.")
                .type(ProviderConfigProperty.ROLE_TYPE).add()
                .build()
        );
    }
    
    @Override
    public ConditionalAuthenticator getSingleton() {
        return ConditionalRoleAuthenticator.SINGLETON;
    }

    @Override
    public String getDisplayType() {
        return "Condition - user not in role";
    }

    @Override
    public String getReferenceCategory() {
        return "condition";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }
    
    private static final Requirement[] REQUIREMENTS_CHOICES = {
            Requirement.REQUIRED, Requirement.DISABLED 
    };

    @Override
    public Requirement[] getRequirementChoices() {
        return REQUIREMENTS_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Flow is executed only if user does not have the given role.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return commonConfig;
    }

    @Override
    public void init(Config.Scope scope) {
        // no-op
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        // no-op
    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
    
    
}
