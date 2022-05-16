package miu.edu.badgesystem.service.Impl;

import miu.edu.badgesystem.dto.request.RoleRequestDTO;
import miu.edu.badgesystem.dto.response.RoleResponseDTO;
import miu.edu.badgesystem.model.Role;
import miu.edu.badgesystem.repository.RoleRepository;
import miu.edu.badgesystem.service.RoleService;
import miu.edu.badgesystem.util.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public RoleResponseDTO findById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NoSuchElementException("Role with id " + roleId + " NOT FOUND"));
        return ModelMapperUtils.map(role, RoleResponseDTO.class);
    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role-> ModelMapperUtils.map(role, RoleResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO save(RoleRequestDTO roleDTO) {
        Role role = ModelMapperUtils.map(roleDTO, Role.class);
        return ModelMapperUtils.map(roleRepository.save(role), RoleResponseDTO.class);
    }

    @Override
    public void delete(Long roleId) {
        //TODO
        Role foundRole = roleRepository.findById(roleId).orElseThrow(() -> new NoSuchElementException("Role with id " + roleId + " NOT FOUND"));
        roleRepository.delete(foundRole);
    }

    @Override
    public RoleResponseDTO update(RoleRequestDTO roleDTO, Long id) {
        Role role = ModelMapperUtils.map(roleDTO, Role.class);
        Role foundRole = roleRepository.findById(id)
                .map(r -> {r.setName(role.getName());
                            r.setDescription(role.getDescription());
                            r.setStatus(role.getStatus());
                            r.setMemberRoles(role.getMemberRoles());
                            return roleRepository.save(r);
                }).orElseThrow(() -> new NoSuchElementException("Role with this id " + id + " not found"));

        return ModelMapperUtils.map(foundRole, RoleResponseDTO.class);
    }
}
