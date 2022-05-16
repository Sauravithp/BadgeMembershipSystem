package miu.edu.badgesystem.service;

import miu.edu.badgesystem.dto.request.RoleRequestDTO;
import miu.edu.badgesystem.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleService {

    RoleResponseDTO findById(Long roleId);

    List<RoleResponseDTO> getAllRoles();

    RoleResponseDTO save(RoleRequestDTO roleRequestDTO);

    void delete(Long roleId);

    RoleResponseDTO update(RoleRequestDTO roleDTO, Long id);

}
