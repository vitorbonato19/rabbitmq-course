package com.java.rabbitmq_springweb.mapper;

import com.java.rabbitmq_springweb.controller.dto.PropostaRequestDto;
import com.java.rabbitmq_springweb.controller.dto.PropostaResponseDto;
import com.java.rabbitmq_springweb.entity.Proposta;
import com.java.rabbitmq_springweb.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-01T22:13:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class PropostaMapperImpl implements PropostaMapper {

    @Override
    public Proposta convertoDtoToProposta(PropostaRequestDto propostaRequestDto) {
        if ( propostaRequestDto == null ) {
            return null;
        }

        Proposta proposta = new Proposta();

        proposta.setUsuario( propostaRequestDtoToUsuario( propostaRequestDto ) );
        proposta.setValorSolicitado( propostaRequestDto.getValorSolicitado() );
        proposta.setPrazoPagamento( propostaRequestDto.getPrazoPagamento() );

        return proposta;
    }

    @Override
    public PropostaResponseDto convertEntityToDto(Proposta proposta) {
        if ( proposta == null ) {
            return null;
        }

        PropostaResponseDto propostaResponseDto = new PropostaResponseDto();

        propostaResponseDto.setNome( propostaUsuarioNome( proposta ) );
        propostaResponseDto.setSobrenome( propostaUsuarioSobrenome( proposta ) );
        propostaResponseDto.setCpf( propostaUsuarioCpf( proposta ) );
        propostaResponseDto.setTelefone( propostaUsuarioTelefone( proposta ) );
        propostaResponseDto.setRenda( propostaUsuarioRenda( proposta ) );
        propostaResponseDto.setId( proposta.getId() );
        if ( proposta.getValorSolicitado() != null ) {
            propostaResponseDto.setValorSolicitado( String.valueOf( proposta.getValorSolicitado() ) );
        }
        propostaResponseDto.setPrazoPagamento( proposta.getPrazoPagamento() );
        propostaResponseDto.setObservacao( proposta.getObservacao() );

        return propostaResponseDto;
    }

    @Override
    public List<PropostaResponseDto> convertListEntityToListDto(Iterable<Proposta> propostas) {
        if ( propostas == null ) {
            return null;
        }

        List<PropostaResponseDto> list = new ArrayList<PropostaResponseDto>();
        for ( Proposta proposta : propostas ) {
            list.add( convertEntityToDto( proposta ) );
        }

        return list;
    }

    protected Usuario propostaRequestDtoToUsuario(PropostaRequestDto propostaRequestDto) {
        if ( propostaRequestDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( propostaRequestDto.getNome() );
        usuario.setSobreNome( propostaRequestDto.getSobrenome() );
        usuario.setCpf( propostaRequestDto.getCpf() );
        usuario.setTelefone( propostaRequestDto.getTelefone() );
        usuario.setRenda( propostaRequestDto.getRenda() );

        return usuario;
    }

    private String propostaUsuarioNome(Proposta proposta) {
        if ( proposta == null ) {
            return null;
        }
        Usuario usuario = proposta.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        String nome = usuario.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private String propostaUsuarioSobrenome(Proposta proposta) {
        if ( proposta == null ) {
            return null;
        }
        Usuario usuario = proposta.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        String sobrenome = usuario.getSobrenome();
        if ( sobrenome == null ) {
            return null;
        }
        return sobrenome;
    }

    private String propostaUsuarioCpf(Proposta proposta) {
        if ( proposta == null ) {
            return null;
        }
        Usuario usuario = proposta.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        String cpf = usuario.getCpf();
        if ( cpf == null ) {
            return null;
        }
        return cpf;
    }

    private String propostaUsuarioTelefone(Proposta proposta) {
        if ( proposta == null ) {
            return null;
        }
        Usuario usuario = proposta.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        String telefone = usuario.getTelefone();
        if ( telefone == null ) {
            return null;
        }
        return telefone;
    }

    private Double propostaUsuarioRenda(Proposta proposta) {
        if ( proposta == null ) {
            return null;
        }
        Usuario usuario = proposta.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Double renda = usuario.getRenda();
        if ( renda == null ) {
            return null;
        }
        return renda;
    }
}
