
// File:   MH_Lexer.java

// Java template file for lexer component of Informatics 2A Assignment 1.
// Concerns lexical classes and lexer for the language MH (`Micro-Haskell').


import java.io.* ;

class MH_Lexer extends GenLexer implements LEX_TOKEN_STREAM {

static class VarAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 1
    // dead = 2
    public String lexClass() { return "VAR"; } ;
    public int numberOfStates() { return 3; } ;

    int next (int state, char c) {
        switch (state) {
            case 0: if (CharTypes.isSmall(c)) return 1; else return 2;
            case 1: if (CharTypes.isSmall(c) || CharTypes.isLarge(c) || CharTypes.isDigit(c) || c == '\'') return 1; else return 2;
            default: return 2;
        }
    }

    boolean accepting (int state) {return (state == 1); }
    int dead() {return 2; }
}

static class NumAcceptor extends Acceptor implements DFA {
    // add code here
    // start = 0
    // accepting = 1 and 2
    // dead = 3
    public String lexClass() { return "NUM"; } ;
    public int numberOfStates() { return 4; } ;

    int next (int state, char c) {
        switch (state) {
            case 0: if (c == '0') return 1; else if (CharTypes.isDigit(c) && (c != 0)) return 2;
            case 1: 
        }
    }

}

static class BooleanAcceptor extends Acceptor implements DFA {
    // add code here
}

static class SymAcceptor extends Acceptor implements DFA {
    // add code here
}

static class WhitespaceAcceptor extends Acceptor implements DFA {
    // add code here
}

static class CommentAcceptor extends Acceptor implements DFA {
    // add code here
}

static class TokAcceptor extends Acceptor implements DFA {

    String tok ;
    int tokLen ;
    TokAcceptor (String tok) {this.tok = tok ; tokLen = tok.length() ;}
    
    // add code here
}

    // add definitions of MH_acceptors here

    MH_Lexer (Reader reader) {
	super(reader,MH_acceptors) ;
    }

}

