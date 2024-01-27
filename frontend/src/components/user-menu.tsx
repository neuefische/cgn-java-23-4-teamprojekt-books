import React from "react";
import { Link } from "react-router-dom";

type UserMenuProps = {
  isLoggedIn: boolean;
  className: string;
};

export const UserMenu: React.FC<UserMenuProps> = ({ isLoggedIn, className }) => {
  return isLoggedIn ? (
    <div className={className}>
      <button className={`h-8 rounded-lg px-3 text-sm`}>Log Out</button>
    </div>
  ) : (
    <div className={`${className} gap-2 text-sm`}>
      <Link to="/login">
        <button className="h-8 rounded-lg px-3">Log In</button>
      </Link>
      <button className="h-8 rounded-lg bg-black px-3 text-white">Sign Up</button>
    </div>
  );
};
