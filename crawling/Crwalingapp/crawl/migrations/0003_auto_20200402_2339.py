# Generated by Django 2.2.6 on 2020-04-02 14:39

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('crawl', '0002_auto_20200402_2327'),
    ]

    operations = [
        migrations.AlterUniqueTogether(
            name='course',
            unique_together={('subject_name', 'professor', 'class_time')},
        ),
    ]
