# Generated by Django 2.0.4 on 2018-06-08 07:59

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('encuestasApp', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='comentario',
            name='encuesta',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='encuestasApp.Encuesta'),
        ),
    ]
