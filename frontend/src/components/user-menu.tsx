import React from "react";
import { Link } from "react-router-dom";
import { cn } from "../lib/utils.ts";

type UserMenuProps = {
  isLoggedIn: boolean;
  className: string;
  logout: () => void;
};

export const UserMenu: React.FC<UserMenuProps> = ({ isLoggedIn, className, logout }) => {
  return isLoggedIn ? (
    <div className={className}>
      <button onClick={logout} className={`h-8 rounded-lg px-3 text-sm`}>
        Log Out
      </button>
    </div>
  ) : (
    <div className={cn("gap-2 text-sm", className)}>
      <Link to="/login">
        <button className="h-8 rounded-lg px-3 w-full">Log In</button>
      </Link>
      <button className="h-8 rounded-lg bg-black px-3 text-white">Sign Up</button>
    </div>
  );
};
