# Generated by Django 2.2.6 on 2020-04-02 14:52

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('crawl', '0003_auto_20200402_2339'),
    ]

    operations = [
        migrations.AlterUniqueTogether(
            name='course',
            unique_together=set(),
        ),
    ]
