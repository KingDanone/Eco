package com.eco.projetoeco.business.service.impl;

import com.eco.projetoeco.business.service.DashboardService;
import com.eco.projetoeco.data.model.enuns.StatusDenuncia;
import com.eco.projetoeco.data.repository.DenunciaRepository;
import com.eco.projetoeco.data.repository.UsuarioRepository;
import com.eco.projetoeco.presentation.dto.admin.DashboardStatsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final DenunciaRepository denunciaRepository;

    @Override
    public DashboardStatsDTO getDashboardStats() {
        long totalUsuarios = usuarioRepository.count();
        long totalDenuncias = denunciaRepository.count();
        long denunciasAbertas = denunciaRepository.countByStatus(StatusDenuncia.ABERTA);
        long denunciasEmAnalise = denunciaRepository.countByStatus(StatusDenuncia.EM_ANALISE);
        long denunciasFechadas = denunciaRepository.countByStatus(StatusDenuncia.FECHADA);

        return DashboardStatsDTO.builder()
                .totalUsuarios(totalUsuarios)
                .totalDenuncias(totalDenuncias)
                .denunciasAbertas(denunciasAbertas)
                .denunciasEmAnalise(denunciasEmAnalise)
                .denunciasFechadas(denunciasFechadas)
                .build();
    }
}
