package br.edu.ifma.labmov.spotted.model;


import java.util.Date;

public class AlunoInfracao {
    private int idAluno;
    private int idInfracao;
    private boolean status;
    private Date dataInfracao;

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getIdInfracao() {
        return idInfracao;
    }

    public void setIdInfracao(int idInfracao) {
        this.idInfracao = idInfracao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDataInfracao() {
        return dataInfracao;
    }

    public void setDataInfracao(Date dataInfracao) {
        this.dataInfracao = dataInfracao;
    }
}
