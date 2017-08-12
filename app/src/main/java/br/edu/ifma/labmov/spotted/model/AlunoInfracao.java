package br.edu.ifma.labmov.spotted.model;


public class AlunoInfracao {
    private int idAluno;
    private int idInfracao;
    private boolean status;

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
}
