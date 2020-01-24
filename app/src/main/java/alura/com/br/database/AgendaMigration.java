package alura.com.br.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import alura.com.br.model.TipoNumero;

import static alura.com.br.model.TipoNumero.Fixo;

class AgendaMigration {


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter table Aluno add column sobreNome text");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table if not exists aluno_ (id integer primary key autoincrement not null," +
                    "nome text," +
                    "telefone text," +
                    "email text)");

            database.execSQL("insert into aluno_ (id, nome, telefone, email) " +
                    "select id, nome, telefone, email from aluno");

            database.execSQL("drop table aluno");

            database.execSQL("alter table aluno_ rename to aluno");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table aluno add column momentoCriacao integer");
            database.execSQL("alter table aluno add column sobreNome text");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_Novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`sobreNome` TEXT, " +
                    "`telefoneFixo` TEXT, " +
                    "`telefoneCelular` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoCriacao` INTEGER)");

            database.execSQL("insert into Aluno_Novo (id, nome, telefoneFixo, email, momentoCriacao) " +
                    "select id, nome, telefone, email, momentoCriacao from aluno");

            database.execSQL("drop table aluno");

            database.execSQL("alter table Aluno_Novo rename to aluno");
        }
    };

    private static final Migration MIGRATION_5_6 = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_Novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`sobreNome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoCriacao` INTEGER)");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT, " +
                    "`alunoId` INTEGER NOT NULL)");

            database.execSQL("Insert into Telefone(numero, alunoId) select telefoneFixo, id from aluno");

            database.execSQL("update Telefone set tipo = ?", new TipoNumero[] {Fixo});

            database.execSQL("drop table aluno");

            database.execSQL("alter table Aluno_Novo rename to aluno");
        }
    };
    static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};


}
