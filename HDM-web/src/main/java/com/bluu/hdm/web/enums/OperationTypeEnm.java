/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluu.hdm.web.enums;

public enum OperationTypeEnm {

    // CONTENTS //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    command, device_table, device_value, disable, enable, get_value, mode_command, parameter, schaman_config, snmp_get,
    snmp_table, snmp_walk, template_config, value,
    // SEQUENCE //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    add_header, add_param, add_value, column_contain, column_delimiter, column_empty, column_equal, column_excluder,
    column_greater, column_greater_or_equal, column_identifier, column_index, column_lower, column_lower_or_equal,
    column_not_contain, column_not_empty, column_not_equal, column_selector, divide_param, divide_value, ends_with, first_column,
    first_row, header_selector, hex_to_ascii, hex_to_ip, just_one_column, just_one_row, list_value, map_param, map_value,
    multiply_param, multiply_value, number_of_columns, number_of_rows, parse_mac, parse_timeticks, reference_column,
    remove_nonwords, remove_pattern, remove_whitespaces, replace_pattern, row_delimiter, row_excluder, row_identifier, row_index,
    row_selector, starts_with, string_to_charlist, subtract_param, subtract_value, to_lower_case, to_upper_case, transpose,
    unique_values, value_length,
    // CHECKS ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    contains, empty, equal, greater, greater_or_equal, lower, lower_or_equal, not_contain, not_empty, not_equal, // starts_with
    // ACTIONS ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    cwmp_set_parameter, cwmp_set_value, device_action, set_parameter, set_value, snmp_set, snmp_set_type, snmp_set_value, wait
}