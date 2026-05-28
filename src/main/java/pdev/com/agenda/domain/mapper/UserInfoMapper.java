package pdev.com.agenda.domain.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import pdev.com.agenda.domain.UserInfoResponse;
import pdev.com.agenda.domain.dto.FormsStatusDTO;
import pdev.com.agenda.domain.dto.UserInfoDTO;
import pdev.com.agenda.domain.dto.UserInfoWithStatusDTO;
import pdev.com.agenda.domain.entity.Role;
import pdev.com.agenda.domain.entity.UserInfo;

/**
 * Mapper entre {@link UserInfo} (entidade JPA) e seus DTOs.
 * <p>
 * Padrão MapStruct (componentModel = "spring") — gera um bean Spring na compilação.
 * Convenções aplicadas neste mapper (template para os próximos a serem migrados):
 * <ul>
 *   <li>{@code unmappedTargetPolicy = IGNORE} — não falha por campo não mapeado (campos sempre
 *       explícitos via @Mapping evitam confusão).</li>
 *   <li>{@code NullValuePropertyMappingStrategy.IGNORE} no update — campos null no DTO não
 *       sobrescrevem o valor existente na entidade.</li>
 *   <li>Resolução de relacionamentos (ex: {@link Role}) é responsabilidade do <b>service</b>, não
 *       do mapper. O mapper apenas marca o campo como {@code ignore = true}.</li>
 *   <li>Comportamentos legados preservados:
 *       <ul>
 *         <li>{@code userName} e {@code password} iniciais = {@code cpf} do usuário.</li>
 *         <li>{@code active = true} e {@code firstLogin = true} no create.</li>
 *       </ul>
 *       Esses comportamentos fazem parte da dívida técnica (ver C2 no CLAUDE.md) e serão
 *       endereçados em outra entrega — por ora, replicamos o que o mapper manual antigo fazia.</li>
 * </ul>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserInfoMapper {

    // ===================== DTO -> Entity =====================

    @Mapping(target = "id", source = "userId")
    @Mapping(target = "userName", source = "cpf")
    @Mapping(target = "password", source = "cpf")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "firstLogin", constant = "true")
    @Mapping(target = "role", ignore = true)    // resolvido pelo service via RoleRepository
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "status", ignore = true)  // setado pelo service como "PENDENTE"
    UserInfo toEntity(UserInfoDTO dto);

    /**
     * Atualização parcial: campos null no DTO NÃO sobrescrevem a entidade.
     * Usado pelo {@code update()} do service.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "firstLogin", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntityFromDto(UserInfoDTO dto, @MappingTarget UserInfo entity);

    // ===================== Entity -> DTO =====================

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "userName", source = "cpf")
    @Mapping(target = "password", source = "cpf")
    @Mapping(target = "isFirstLogin", constant = "true")
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "roleName", source = "role", qualifiedByName = "roleToName")
    UserInfoDTO toDTO(UserInfo entity);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "userName", source = "cpf")
    @Mapping(target = "isFirstLogin", constant = "true")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "roleName", source = "role", qualifiedByName = "roleToNameOrSemPerfil")
    UserInfoResponse toResponseDTO(UserInfo entity);

    default UserInfoWithStatusDTO toWithStatusDTO(UserInfo entity, FormsStatusDTO formsStatus) {
        return new UserInfoWithStatusDTO(toDTO(entity), formsStatus);
    }

    // ===================== Helpers nomeados =====================

    @Named("roleToName")
    default String roleToName(Role role) {
        if (role == null || role.getName() == null) {
            return null;
        }
        return role.getName().name();
    }

    @Named("roleToNameOrSemPerfil")
    default String roleToNameOrSemPerfil(Role role) {
        if (role == null || role.getName() == null) {
            return "SEM_PERFIL";
        }
        return role.getName().name();
    }
}
