package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.RoleRequestDTO;
import miu.edu.badgesystem.dto.response.RoleResponseDTO;
import miu.edu.badgesystem.exception.DataDuplicationException;
import miu.edu.badgesystem.exception.NoContentFoundException;
import miu.edu.badgesystem.model.Member;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.RoleRepository;
import miu.edu.badgesystem.service.RoleService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public RoleResponseDTO findById(Long roleId) {
        Role role = roleRepository.getActiveRoleByID(roleId).orElseThrow(() -> {
            throw new NoContentFoundException("No Content found");
        });
        return ModelMapperUtils.map(role, RoleResponseDTO.class);
    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        List<Role> roles = roleRepository.getActiveAllRoles();
        if (roles.isEmpty()) {
            throw new NoContentFoundException("Role(s) is empty, No data found");
        }
        return roles.stream().map(role -> ModelMapperUtils.map(role, RoleResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO save(RoleRequestDTO roleDTO) {
        Role role = roleRepository.getRoleByName(roleDTO.getName());
        if (Objects.nonNull(role)) {
            throw new DataDuplicationException("Role with name" + roleDTO.getName() + "already exists");
        }
        Role roleToSave = ModelMapperUtils.map(roleDTO, Role.class);
        roleToSave.setStatus('Y');
        return ModelMapperUtils.map(roleRepository.save(roleToSave), RoleResponseDTO.class);
    }

    @Override
    public void delete(Long roleId) {
        //TODO
        Role foundRole = roleRepository.findById(roleId).orElseThrow(() -> {
            throw new NoContentFoundException("No Content Found");
        });
        foundRole.setStatus('D');
        roleRepository.save(foundRole);
    }

    @Override
    public RoleResponseDTO update(RoleRequestDTO roleDTO, Long id) {
        Role role = ModelMapperUtils.map(roleDTO, Role.class);
        Role alreadyRole = roleRepository.getUpdateRoleByName(roleDTO.getName(), id);

        if (Objects.nonNull(alreadyRole)) {
            throw new DataDuplicationException("Role with name" + alreadyRole.getName() + "already exists");
        }
        Role foundRole = roleRepository.findById(id)
                .map(r -> {
                    r.setName(role.getName());
                    r.setDescription(role.getDescription());
                    r.setStatus(role.getStatus());
             //       r.setMemberRoles(role.getMemberRoles());
                    return roleRepository.save(r);
                }).orElseThrow(() -> {
                    throw new NoContentFoundException("No content found");
                });

        return ModelMapperUtils.map(foundRole, RoleResponseDTO.class);
    }
}
