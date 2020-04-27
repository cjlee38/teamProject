from django import forms
from django.contrib import admin
from django.contrib.auth.models import Group
from django.contrib.auth.admin import UserAdmin as BaseUserAdmin
from django.contrib.auth.forms import ReadOnlyPasswordHashField
from .models import User, Instruction

class LogAdmin(admin.ModelAdmin):
    list_display = ('action_time','user','content_type','change_message','is_addition','is_change','is_deletion')
    list_filter = ['action_time','user','content_type']
    ordering = ('-action_time',)
    #We don't want people changing this historical record:
    def has_add_permission(self, request):
        return False
    def has_change_permission(self, request, obj=None):
        #returning false causes table to not show up in admin page :-(
        #I guess we have to allow changing for now
        return True
    def has_delete_permission(self, request, obj=None):
        return False

class UserCreationForm(forms.ModelForm):
    password1 = forms.CharField(label='Password', widget=forms.PasswordInput)
    password2 = forms.CharField(label='Password confirmation', widget=forms.PasswordInput)

    class Meta:
        model = User
        fields = ('user_id', 'name', 'student_number')

    def clean_password2(self):
        password1 = self.cleaned_data.get("password1")
        password2 = self.cleaned_data.get("password2")
        if password1 and password2 and password1 != password2:
            raise forms.ValidationError("Passwords don't match")
        return password2

    def save(self, commit=True):
        user = super().save(commit=False)
        user.set_password(self.cleaned_data["password1"])
        if commit:
            user.save()
        return user


class UserChangeForm(forms.ModelForm):
    password = ReadOnlyPasswordHashField()
    class Meta:
        model = User
        fields = ('user_id', 'password', 'name', 'student_number', 'is_admin')
    def clean_password(self):
        return self.initial["password"]


class UserAdmin(BaseUserAdmin):
    form = UserChangeForm
    add_form = UserCreationForm

    list_display = ('user_id', 'name', 'student_number', 'is_admin')
    list_filter = ('is_admin',)
    fieldsets = (
        (None, {'fields': ('student_number', 'password')}),
        ('Personal info', {'fields': ('name', 'user_id')}),
        ('Permissions', {'fields': ('is_admin',)}),
    )
    add_fieldsets = (
        (None, {
            'classes': ('wide',),
            'fields': ('user_id', 'name', 'student_number', 'password1', 'password2'),
        }),
    )
    search_fields = ('student_number',)
    ordering = ('student_number',)
    filter_horizontal = ()

class Course_Admin(admin.ModelAdmin):
    search_fields = ['id', 'the_image', 'title', 'product_image_gallery__sku']



admin.site.register(User, UserAdmin)
admin.site.register(Instruction)
admin.site.unregister(Group)
