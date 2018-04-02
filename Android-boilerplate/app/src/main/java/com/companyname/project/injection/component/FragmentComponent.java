package com.companyname.project.injection.component;

import com.companyname.project.injection.PerFragment;
import com.companyname.project.injection.module.FragmentModule;

import dagger.Subcomponent;

/**
 * This component inject dependencies to all Fragments across the application
 */

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}
